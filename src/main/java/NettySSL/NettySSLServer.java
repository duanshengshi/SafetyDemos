package NettySSL;

import NettySSL.handler.SecureServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.util.Date;

public class NettySSLServer {
    private static final int PORT = 9527;

    private EventLoopGroup bossGroup = null ;

    private EventLoopGroup workerGroup = null ;

    public void start(){
        bossGroup = new NioEventLoopGroup() ;
        workerGroup = new NioEventLoopGroup() ;
        try {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            SSLEngine engine = SecureSslContextFactory.getServerContext().createSSLEngine();
                            engine.setUseClientMode(false);
                            engine.setNeedClientAuth(true);
                            pipeline.addFirst("ssl", new SslHandler(engine));
                            // On top of the SSL handler, add the text line codec.
                            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // and then business logic.
                            pipeline.addLast("handler", new SecureServerHandler());
                        }
                    });
            bind(serverBootstrap,PORT);
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully() ;
            workerGroup.shutdownGracefully() ;
        }
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }

    public static void main(String[] args){
        NettySSLServer nettySSLServer = new NettySSLServer();
        nettySSLServer.start();
    }

}

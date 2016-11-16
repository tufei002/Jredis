package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

	public static void main(String[] args) throws InterruptedException {
		new TimeClient().connect(8080, "127.0.0.1");
	}

	public void connect(int port, String host) throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(bossGroup)
			 .channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .handler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
//					arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));//以回车符结束
					arg0.pipeline().addLast(new FixedLengthFrameDecoder(20));//以固定长度结束
//					ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//					arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));//以指定分隔符结束
					
					arg0.pipeline().addLast(new StringDecoder());
					arg0.pipeline().addLast(new TimeClientHandler());
					
				}});
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
		}
		
	}
}

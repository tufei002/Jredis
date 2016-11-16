package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {

	public static void main(String[] args) throws InterruptedException {
		int port = 8080 ;
		new TimeServer().bind(port);

	}
	
	public void bind(int port) throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workGroup)
			 .channel(NioServerSocketChannel.class)
			 .option(ChannelOption.SO_BACKLOG, 1024)
			 .childHandler(new ChildChannelHandler());
			
			
			ChannelFuture cf = b.bind(port).sync();
			System.out.println("id : "+cf.channel().id());
			cf.channel().closeFuture().sync();
		}finally{
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
//			arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));//�Իس�������
			arg0.pipeline().addLast(new FixedLengthFrameDecoder(20));//�Թ̶����Ƚ���
//			ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//			arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));//��ָ���ָ�������
			
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new TimeServerHandler());
			
		}
		
	}

}

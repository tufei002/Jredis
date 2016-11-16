package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter{

	private int counter;
	private byte[] req;
//	private final ByteBuf firstMsg;
	
	public TimeClientHandler(){
//		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
		req = ("QUERY TIME ORDER"+"$_").getBytes();
//		firstMsg = Unpooled.buffer(req.length);
//		firstMsg.writeBytes(req);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "utf-8");
		String body = (String)msg;
		System.out.println("Now is : "+ body + ", the couter is "+ ++counter);
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
//		ctx.writeAndFlush(firstMsg);
		ByteBuf msg = null;
		for(int i=0;i<100;i++){
			msg = Unpooled.buffer(req.length);
			msg.writeBytes(req);
			ctx.writeAndFlush(msg);
		}
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t){
		System.out.println("------------exception------------");
		ctx.close();
	}
}

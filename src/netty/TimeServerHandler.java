package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter{
	private int counter;
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
		String body = (String)msg;
//		System.out.println(req.length+"the server receive order : "+ new String(req, "utf-8"));
		System.out.println(body.length()+ " ,the server receive order : "+ body+ ",the counter is "+ ++counter);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
				?new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
//		currentTime = currentTime+System.getProperty("line.separator");
		currentTime = currentTime+"$_";
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t){
		ctx.close();
	}
}

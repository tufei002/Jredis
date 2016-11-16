package test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.util.internal.SystemPropertyUtil;

public class TestStaticFinal {
	private static final String strStaticFinalVar = "aaa";
	private static int strStaticVar;
	private final String strFinalVar = null;
	private static final int intStaticFinalVar = 0;
	private static final Integer integerStaticFinalVar = new Integer(8);
	private static final ArrayList<String> alStaticFinalVar = new ArrayList<String>();

	private void test() {
		System.out.println("-------------ֵ����ǰ----------\r\n");
		System.out.println("strStaticFinalVar=" + strStaticFinalVar + "\r\n");
		System.out.println("strStaticVar=" + strStaticVar + "\r\n");
		System.out.println("strFinalVar=" + strFinalVar + "\r\n");
		System.out.println("intStaticFinalVar=" + intStaticFinalVar + "\r\n");
		System.out.println("integerStaticFinalVar=" + integerStaticFinalVar + "\r\n");
		System.out.println("alStaticFinalVar=" + alStaticFinalVar + "\r\n");

		// strStaticFinalVar="��������"; //����final��ʾ��̬,�����Ըı��������.
		strStaticVar = 4444; // ��ȷ��static��ʾ�����,ֵ���Ըı�.
//		 strFinalVar="�ǺǺǺ�"; //����, final��ʾ��̬���ڶ����ʱ���Ҫ��ֵ�����¸���null����һ��������Ͳ����ٸ��ġ�
//		 intStaticFinalVar=2; //����,
		// final��ʾ��̬���ڶ����ʱ���Ҫ��ֵ�����¸���null����һ��������Ͳ����ٸ��ġ�
//		 integerStaticFinalVar=new Integer(8); //����,
		// final��ʾ��̬���ڶ����ʱ���Ҫ��ֵ�����¸���null����һ��������Ͳ����ٸ��ġ�
		alStaticFinalVar.add("aaa"); // ��ȷ��������������û�б仯����������ݷ����˱仯����������Ƿǳ����õģ��кܶ���;��
		alStaticFinalVar.add("bbb"); // ��ȷ��������������û�б仯����������ݷ����˱仯����������Ƿǳ����õģ��кܶ���;��

		System.out.println("-------------ֵ�����----------\r\n");
		System.out.println("strStaticFinalVar=" + strStaticFinalVar + "\r\n");
		System.out.println("strStaticVar=" + strStaticVar + "\r\n");
		System.out.println("strFinalVar=" + strFinalVar + "\r\n");
		System.out.println("intStaticFinalVar=" + intStaticFinalVar + "\r\n");
		System.out.println("integerStaticFinalVar=" + integerStaticFinalVar + "\r\n");
		System.out.println("alStaticFinalVar=" + alStaticFinalVar + "\r\n");
		System.out.println(64 >> 3);
		System.out.println(Math.min(31, 4));
		System.out.println(4 & 13);//0100 & 1101 -->  0100
		System.out.println(0x1F); //1*16+15*1
		System.out.println(0xFFFF); //15*16^3+15*16^2+15*16^1+15*16^0  ==65535
		System.out.println(2 >>> 1);
		System.out.println(-2 >>> 1);
		
		System.out.println(TEST_INTEGER.incrementAndGet());
		System.out.println(SystemPropertyUtil.getInt("io.netty.threadLocalDirectBufferSize", 64 * 1024));
		System.out.println((int) (Thread.currentThread().getId() % Runtime.getRuntime().availableProcessors()));
		System.out.println((Thread.currentThread().getId()+","+Runtime.getRuntime().availableProcessors()));
	}
	public final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);  

	public static void main(String args[]) {
		new TestStaticFinal().test();
	}
}
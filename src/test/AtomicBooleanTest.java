package test;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {
	 /** 
     * ��Ҫ������ 
     * @see AtomicBoolean#compareAndSet(boolean, boolean)  ��һ������Ϊԭʼֵ���ڶ�������ΪҪ�޸ĵ���ֵ�����޸ĳɹ��򷵻�true�����򷵻�false 
     * @see AtomicBoolean#getAndSet(boolean)   ���������µ�booleanֵ��ֱ���ɹ�Ϊֹ����������ǰ������ 
     */  
    public final static AtomicBoolean TEST_BOOLEAN = new AtomicBoolean(true);  
      
    public static void main(String []args) {  
        for(int i = 0 ; i < 10 ; i++) {  
            new Thread() {  
                public void run() {  
                    try {  
                        Thread.sleep(1000);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    if(TEST_BOOLEAN.compareAndSet(true, false)) {  
                        System.out.println("�ҳɹ��ˣ�");  
                    }else{
                    	System.out.println("��ʧ���ˣ�");  
                    }
                }  
            }.start();  
        }  
    }  
}

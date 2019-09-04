package com.xwj.artOfConcurrency.chapter4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Description 管道输入/输出
 * @Author yuki
 * @Date 2019/2/21 19:42
 * @Version 1.0
 **/
public class Piped {
    public static void main(String[] args) throws IOException {
        PipedReader reader=new PipedReader();
        PipedWriter writer=new PipedWriter();
        reader.connect(writer);
        Thread printThread = new Thread(new Print(reader), "PrintThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                writer.write(receive);
            }
        } finally {
            writer.close();
        }
    }
    static class Print implements Runnable{
        private PipedReader reader;
        Print(PipedReader reader){
            this.reader=reader;
        }
        @Override
        public void run() {
            int receive=0;
            try {
                while ((receive=reader.read()) !=-1){
                    System.out.print((char) receive);
                }
            }catch (IOException e){}
        }
    }
}

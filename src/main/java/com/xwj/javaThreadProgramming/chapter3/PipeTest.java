package com.xwj.javaThreadProgramming.chapter3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Description 管道实现线程通信
 * @Author yuki
 * @Date 2019/1/2 14:47
 * @Version 1.0
 **/
class WriteData{
    public void writeMethod(PipedOutputStream outputStream) throws IOException {
        System.out.println("write:");
        for (int i=0;i<300;i++){
            String outData=""+(i+1);
            outputStream.write(outData.getBytes());
            System.out.print(outData);
        }
        System.out.println();
        outputStream.close();
    }
}
class ReadData{
    public void readMethod(PipedInputStream inputStream) throws IOException {
        System.out.println("read:");
        byte[] bytes=new byte[20];
        int readLength=inputStream.read(bytes);
        while (readLength!=-1){
            String newData=new String(bytes,0,readLength);
            System.out.print(newData);
            readLength=inputStream.read(bytes);
        }
        System.out.println();
        inputStream.close();
    }
}
class ThreadWrite extends Thread{
    private WriteData writeData;
    private PipedOutputStream outputStream;
    ThreadWrite(WriteData writeData,PipedOutputStream outputStream){
        this.outputStream=outputStream;
        this.writeData=writeData;
    }
    @Override
    public void run() {
        try {
            writeData.writeMethod(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ThreadRead extends Thread{
    private ReadData readData;
    private PipedInputStream inputStream;
    ThreadRead(ReadData readData,PipedInputStream inputStream){
        this.inputStream=inputStream;
        this.readData=readData;
    }
    @Override
    public void run() {
        try {
            readData.readMethod(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class PipeTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        WriteData writeData=new WriteData();
        ReadData readData=new ReadData();
        PipedInputStream inputStream=new PipedInputStream();
        PipedOutputStream outputStream=new PipedOutputStream();
        outputStream.connect(inputStream);
        ThreadRead read=new ThreadRead(readData,inputStream);
        read.start();
        Thread.sleep(2000);
        ThreadWrite write=new ThreadWrite(writeData,outputStream);
        write.start();
    }
}

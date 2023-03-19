package main.java.curs4;

import java.util.GregorianCalendar;

public class exemple_string {
    public static void main (String[] args){
        long start = new GregorianCalendar().getTimeInMillis();

        Runtime rt = Runtime.getRuntime();
        long free_mem = rt.freeMemory();

        StringBuilder strbld = new StringBuilder();
        for (int i = 0; i < 10_000_000; i++){
            strbld.append(":").append(i); // viteze f bune pt appenduri repetate
        }

        StringBuffer strbuf = new StringBuffer();
        for (int i = 0; i < 10_000_000; i++){
            strbld.append(":").append(i);
        }

        // StringBuilder : Mem 22 megab => Timp exec 0,46 sec
        // StringBuffer : Mem la fel => Timp exec cu 50% mai mare

        System.out.println(strbuf.length()); // 78 mil caract creat in 0.7 sec

        String str = "";
        for (int i = 0; i < 10_000_000; i++){
            str += ":" + i; // nu e eficient pt concatenari repetate; am ajuns la 526 de mega ocupati in mem
        }

    }
}
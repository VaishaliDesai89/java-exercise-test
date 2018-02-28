package junitgrouptest;

import java.io.File;
import java.net.URLConnection;

import org.junit.Test;
public class ScanFolder {
	final String directoryPath ="src/vehicle-information";
	
	@Test
    public void listofAllFiles(){
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
        System.out.println("List of all files");
        for (File file : fList){
            System.out.println("File name is " + file.getName());
        }
    }

	@Test
    public void listofFileMimeTypes(){
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
        System.out.println("Mimetype of all files");
        for (File file : fList){
        	System.out.println("Mimetype for file" + file.getName() +" is:" + URLConnection.guessContentTypeFromName(file.getName()));
        }
    }
	
	@Test
    public void sizeOfFiles(){
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
        System.out.println("Size of all files");
        for (File file : fList){
            System.out.println("Size of "+ file.getName() + " is " + file.length());
        }
    }
	
	@Test
	public void extensionOfFiles() {
		File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
        System.out.println("Extension of all files");
        for (File file : fList) {
        	String name = file.getName();
        	System.out.println("Extension of "+ file.getName() + " is " + (name.substring(name.lastIndexOf(".") + 1)));
        }
	}
}

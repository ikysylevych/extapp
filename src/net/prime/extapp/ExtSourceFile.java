package net.prime.extapp;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class ExtSourceFile {

    private File file = null;
    private String webPath = null;
    private String contents = null;

    private Integer rank = 0;
    private Boolean processed = true;
    private Boolean isFile = true;
    
    private Map<String, Boolean> dependentExtClasses = new HashMap<String, Boolean>();

    
    /**
     * Creates a new SourceFile based on a file.
     * @param file
     */
    ExtSourceFile(String fullpath, String webPath, String charset){
        File file;
        FileInputStream stream;
        String contents;

        this.webPath = webPath;

        try {
            file = new File(fullpath);
            stream = new FileInputStream(file);
            contents = IOUtils.toString(stream, charset);            
            
            this.file = file;
            this.contents = contents;

        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            this.isFile = false;
        } catch (Exception e) { 
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    public File getFile() {
        return file;
    }
    
    public String getFilePath(){
        return file.getAbsolutePath();
    }
    
    public String getDirectory(){
        String path = file.getAbsolutePath();
        return path.substring(0, path.lastIndexOf(File.separator)+1);
    }

    public String getWebPath() {
        return webPath;
    }
    

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank; 
    }

    public Boolean isProcessed() {
        return processed;
    }
    
    public void setProcessed(Boolean isProcessed) {
        this.processed = isProcessed;
    }
    
    public void setProcessed() {
        setProcessed(true);
    }
    
    public Boolean isFile() {
        return isFile;
    }
    
    public void addDependentExtClasses(List<String> depExtClasses) {
        for (String depExtClass : depExtClasses) {
            if (this.dependentExtClasses.get(depExtClass) == null) {
                this.dependentExtClasses.put(depExtClass, true);
            }
        }
    }
    
    public boolean hasDependentExtClass(String extClass) {
        Boolean hasExtClass = this.dependentExtClasses.get(extClass);
        return hasExtClass != null ? hasExtClass : false;
    }
}

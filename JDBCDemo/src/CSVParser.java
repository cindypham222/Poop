
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rbrown
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVParser {
    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';
    
    private final char separator, quote;
    private final Scanner scanner;
    
    private CSVParser(File file,char separator,char quote) throws FileNotFoundException {
        this.scanner = new Scanner(file);
        this.separator = separator;
        this.quote = quote;
    }
    
    public static CSVParser parseFile(String path) throws FileNotFoundException {
        return CSVParser.parseFile(new File(path),SEPARATOR,QUOTE);
    }
    public static CSVParser parseFile(String path,char separator) throws FileNotFoundException {
        return CSVParser.parseFile(new File(path),separator,QUOTE);
    }
    public static CSVParser parseFile(String path,char separator,char quote) throws FileNotFoundException {
    	return CSVParser.parseFile(new File(path),separator,QUOTE);
    }
    public static CSVParser parseFile(File file) throws FileNotFoundException {
        return CSVParser.parseFile(file,SEPARATOR,QUOTE);
    }
    public static CSVParser parseFile(File file,char separator) throws FileNotFoundException {
        return CSVParser.parseFile(file,separator,QUOTE);
    }
    public static CSVParser parseFile(File file,char separator,char quote) throws FileNotFoundException {
        return new CSVParser(file,separator,quote);
    }
    
    public void close() {
        scanner.close();
    }
    
    public boolean hasNext() {
        return scanner.hasNext();
    }
    
    public List<String> nextLine() throws Exception {
        return parseLine(scanner,separator,quote);
    }
    
    public static List<String> parseLine(String line) throws Exception {
        return parseLine(line,SEPARATOR,QUOTE);
    }

    public static List<String> parseLine(String line,char separator) throws Exception {
        return parseLine(line,separator,QUOTE);
    }

    public static List<String> parseLine(String line,char separator,char quote) throws Exception {
        List<String> result = new ArrayList<>();

        if ((line == null) || (line.isEmpty()))
            return result;

        StringBuffer buffer = new StringBuffer();
        
        if (separate(line,result,buffer,false,separator,quote)) {
            throw new Exception("Unquoted EOL in line at column " + result.size());
        }
        
        return result;
    }

    public static List<String> parseLine(Scanner scanner,char separator,char quote) throws Exception {
        List<String> result = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        boolean inQuote = false;
        String line;
        
        do {
            line = scanner.nextLine();
        
            if ((line == null) || (line.isEmpty()))
                if (inQuote)
                    throw new Exception("Unquoted EOL in line at column " + result.size());
                else
                    return result;
        } while(separate(line,result,buffer,inQuote,separator,quote) && (inQuote = true));
        
        return result;
    }

    private static boolean separate(String line,List<String> result,StringBuffer buffer,boolean inQuote,char separator,char quote) throws Exception {
        char[] chars = line.toCharArray();
        boolean buildEscapedQuote = false;
        
        for (char c:chars) {
            if (c == separator) {
                if (inQuote && !buildEscapedQuote) {
                    buffer.append(c);
                } else {
                    result.add(buffer.toString());
                    buffer = new StringBuffer();
                    inQuote = buildEscapedQuote = false;
                }
            } else if (c == quote) {
                if (buffer.length() == 0) {
                    inQuote = true;
                } else if (buildEscapedQuote) {
                    buffer.append(quote);
                    buildEscapedQuote = false;
                } else {
                    buildEscapedQuote = true;
                }
            } else if (buildEscapedQuote) {
                throw new Exception("Malformed CSV at column " + result.size());
            } else {
                buffer.append(c);
            }
        }
        
        if (!inQuote || buildEscapedQuote)
            result.add(buffer.toString());

        return inQuote && !buildEscapedQuote;
    }
}
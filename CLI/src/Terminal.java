/**
 * Team Members
 *          Name                            ID
 * 1-Ahmed Mohamed Abdelwahed            20180029
 * 2-Omneya Eid Mohamed Sayed            20180054
 * 3-Ahmed Atya Hassan                   20180013
 * 4-Eslam Ramadan Abdo                  20180044
 
 */
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Terminal {

    String hostname = "Unknown";
    public String currentDirectory = "C:\\Users\\" + System.getProperty("user.name") + "\\";

    /*
     if you want to test cp write this command 
     cp path of first fill path of second fiel
     like  cp test\test.txt test\test,txt
     */
    public void cp(String sourcePath, String destinationPath) {
        if (!sourcePath.contains(":") && !sourcePath.equals("")) {
            sourcePath = currentDirectory + sourcePath + "\\";
        }
        if (!destinationPath.contains(":") && !destinationPath.equals("")) {
            destinationPath = currentDirectory + destinationPath + "\\";
        }
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File source = new File(sourcePath);
            File dest = new File(destinationPath);
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mkdir(String dirname) {

        File newdir = new File(dirname);
        if (!newdir.exists()) {
            newdir.mkdir();
        } else {
            System.out.println("there is a directory with same name");
        }
    }
    /*
     * @prototype function: public void clear();
     * 
     * @argument: command
     * 
     * @functionality: clear current terminal screen
     */

    public void clear() {
        for (int clear = 0; clear < 1000; clear++) {
            System.out.println();
        }
    }
    /*
     * @prototype function: public pwd(String path, int flag)
     * 
     * @argument: command
     * 
     * @functionality: print the current directory
     */

    public void pwd(String path, int flag) throws IOException {
        if ((!path.contains(":")) && !path.equals("")) {
            path = currentDirectory + path + "\\";
        }
        switch (flag) {
            case 0:
                System.out.println(currentDirectory);
                break;
            case 1:
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(path));
                writer.write(currentDirectory);
                writer.newLine();
                writer.close();
                break;
            case 2:
                String temp = currentDirectory + System.lineSeparator();
                Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                break;
            default:
                break;
        }
    }

    public String cd(String perviousdirectory, String newdirectory) {
        // 
        if (newdirectory.equals("..")) // back to the parent directory
        {
            File f = new File(perviousdirectory);
            perviousdirectory = f.getParent();
        } else if (newdirectory.contains(":")) // absolute path
        {
            perviousdirectory = newdirectory;
        } else {
                            // user/ahmed/space
            // user/ahmed
            int lastIndex = perviousdirectory.lastIndexOf("\\");
            File f = new File(perviousdirectory.substring(lastIndex + 1));
            File[] files = f.listFiles();

            for (File currFile : files) {
                if (currFile.getName().equals(newdirectory)) {
                    perviousdirectory = perviousdirectory + "\\" + newdirectory;
                }
                /**
                 * 
                 */
            }

        }
        return perviousdirectory;
    }

    public void rmdir(String directoryrm) {
        File fremove = new File(directoryrm);
        if (fremove.exists()) {
            fremove.delete();
        } else {
            System.out.println("you are deleting not existing directory");
        }
    }

    public void ls(String Directory, String filePath, int flag) {
        if (!filePath.contains(":") && !filePath.equals("")) {
            filePath = currentDirectory + filePath + "\\";
        }
        try {
            File currentdir = new File(Directory);
            File[] filels = currentdir.listFiles();
            switch (flag) {
                case 0:
                    for (File file : filels) {
                        System.out.println(file.getName());
                    }
                    break;
                case 1:
                    BufferedWriter writer;
                    writer = new BufferedWriter(new FileWriter(filePath));
                    for (File file : filels) {
                        writer.write(file.getName());
                        writer.newLine();
                    }
                    writer.close();
                    break;
                case 2:
                    for (File file : filels) {
                        String temp = file.getName() + System.lineSeparator();
                        Files.write(Paths.get(filePath), temp.getBytes(), StandardOpenOption.APPEND);
                    }
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    /*
     if you want to test mv write this command 
     mv path of first fill path of second fiel
     like  mv test\test.txt test\test,txt
     */

    public void mv(String FirstPath, String SecondPath) {
        if (!FirstPath.contains(":") && !FirstPath.equals("")) {
            FirstPath = currentDirectory + FirstPath + "\\";
        }
        if (!SecondPath.contains(":") && !SecondPath.equals("")) {
            SecondPath = currentDirectory + SecondPath + "\\";
        }
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File firstfile = new File(FirstPath);
            File secondfile = new File(SecondPath);
            inStream = new FileInputStream(firstfile);
            outStream = new FileOutputStream(secondfile);
            byte[] buffer = new byte[1024];
            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            //delete the original file
            firstfile.delete();
            System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * @ prototype function: public void gettingCurDate();
     * 
     * @argument : no args
     * 
     * @ functionality: print date and time
     */

    public void gettingCurDate(String path, int flag) throws IOException {
        if (!path.contains(":") && !path.equals("")) {
            path = currentDirectory + path + "\\";
        }
        DateFormat formatDisplay = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar curDate = Calendar.getInstance();
        switch (flag) {
            case 0:
                System.out.println(formatDisplay.format(curDate.getTime()));
                break;
            case 1:
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(path));
                writer.write(formatDisplay.format(curDate.getTime()));
                writer.newLine();
                writer.close();
                break;
            case 2:
                String temp = formatDisplay.format(curDate.getTime()) + System.lineSeparator();
                Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                break;
            default:
                break;
        }
    }
    /*
     if you want to test rm write this command 
     rm path of fill
     like  rm test\test.txt
     */

    public void rm(String path) {
        if (!path.contains(":") && !path.equals("")) {
            path = currentDirectory + path + "\\";
        }
        try {
            File file = new File(path); // delete file
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     if you want to test cat write this command 
     cat path of first fill 
     like  cat test\test.txt
     */

    public void cat(String path1, String path2) throws IOException {
        

        try {
            if (!path1.contains(":") && !path1.equals("")) {
            path1 = currentDirectory + path1 + "\\";
            }
            if (!path2.contains(":") && !path2.equals("")) {
                path2 = currentDirectory + path2 + "\\";
            }
            File first_file = new File(path1);
            File second_file = new File(path2);
            if (first_file.exists() && second_file.exists()) {
                Scanner input = new Scanner(first_file);

                StringBuilder file_content = new StringBuilder();
                while (input.hasNextLine()) {
                    file_content.append(input.nextLine());
                }
                file_content.append("\n");
                
                Files.write(second_file.toPath(), file_content.toString().getBytes(), StandardOpenOption.APPEND);
            } else {
                System.out.println("You should pass two Files");
            }

            Scanner input = new Scanner(second_file);
            while (input.hasNextLine()) {
                String data = input.nextLine();
                System.out.println(data + "\n");
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

//                while ((line = in.readLine()) != null) {
//                        System.out.println(line);
//                }
//		in.close();
//                
//                
//		BufferedReader in1 = new BufferedReader(new FileReader(path2));
//		String linee;
//                while ((linee = in1.readLine()) != null) {
//                        System.out.println(linee);
//                }
//		in1.close();
    }

    /*
     *  user may enter filePath: absolute path or relative path 
     *  1- absolute path /home/user/Doc/test.txt (NO PROBLEM)
     *  2- relative path ./test.txt  or test.txt
     *
     * @ prototype function: private boolean checkPathType(String filePath);
     * 
     * @argument: filePath 
     * 
     * @functionality: return true if filePath is relative otherwise return false
     */
    private boolean checkPathType(String filePath) {
        int flag = -1;
        flag = filePath.indexOf("\\");
        if (filePath.contains(".\\") || flag == -1) {
            return true; // relative path
        } else {
            return false; // absolute path
        }
    }

    /*
     * @ prototype function: public void more(String filePath);
     * 
     * @ argument: string filePath
     * 
     * @functionality: read content of file if it exist
     *
     */
    public void more(String filePath) {
        File fileRef = null;
        // check filePath ? relative or absolute
        if (checkPathType(filePath)) {
            // convert relative path into absolute path 
            filePath = currentDirectory + "\\" + filePath.substring(filePath.indexOf("\\") + 1);
            fileRef = new File(filePath);
        } else {
            fileRef = new File(filePath);
        }
        if (fileRef.exists()) // check if file exist or not
        {
            if (fileRef.isDirectory()) {
                System.out.println("Command more only reads file.");
            } else {
                try {
                    BufferedReader buffer = null;
                    buffer = new BufferedReader(new FileReader(fileRef)); // read from file
                    String line = null;
                    try {

                        while ((line = buffer.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("IOException");
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFoundException");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Parent can't reach to your file");
        }
    }
    /*
     * @prototype function: public void args(String command);
     * 
     * @argument: command
     * 
     * @functionality: print args for specific command
     */

    public void args(String cmd, Parser as, String path, int flag) throws IOException {
        if (!path.contains(":") && !path.equals("")) {
            path = currentDirectory + path + "\\";
        }
        switch (flag) {
            case 0:
                System.out.println(cmd);
                break;
            case 1:
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(path));
                writer.write(cmd);
                writer.newLine();
                writer.close();
                break;
            case 2:
                String temp = cmd + System.lineSeparator();
                Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                break;
            default:
                break;
        }
        if (as.listOfCmd.contains(cmd)) {
            if (null != as.num.get(as.listOfCmd.indexOf(cmd))) {
                switch (as.num.get(as.listOfCmd.indexOf(cmd))) {
                    case 0:
                        if (flag == 0) {
                            System.out.println(" Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args ");
                        } else if (flag == 1 || flag == 2) {
                            String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args "
                                    + System.lineSeparator();
                            Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                        }
                        break;
                    case 1:
                        if (flag == 0) {
                            System.out.println(
                                    " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args : Destination Path");
                        } else if (flag == 1 || flag == 2) {
                            String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
                                    + " args : Destination Path" + System.lineSeparator();
                            Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                        }
                        break;
                    case 2:
                        if (flag == 0) {
                            System.out.println(" Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
                                    + " args : Source Path, Destination Path");
                        } else if (flag == 1 || flag == 2) {
                            String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
                                    + " args : Source Path, Destination Path" + System.lineSeparator();
                            Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("Wrong Command!");
        }
    }
    /*
     * @prototype function: public void help(String path, int flag);
     * 
     * @argument: command
     * 
     * @functionality: show all commands and their work
     */

    public void help(String path, int flag) throws IOException {
        if (!path.contains(":") && !path.equals("")) {
            path = currentDirectory + path + "\\";
        }
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("help ---> This command show to user what every command do.");
        cmd.add("clear ---> removes current terminal screen.");
        cmd.add("cp ---> copy content of a file to another one.");
        cmd.add("ls ---> show the folders and files in the current directory.");
        cmd.add("pwd ---> show the current directory.");
        cmd.add("cd ---> change the current directory.");
        cmd.add("mv ---> cut and move content of a file to another one.");
        cmd.add("rm ---> delete file  you entered its path.");
        cmd.add("mkdir ---> create new folder on entered path.");
        cmd.add("rmdir ---> remove folder from entered path.");
        cmd.add("cat ---> read the content of file.");
        cmd.add("date ---> get current time and date.");
        cmd.add("more  --> This command Let us display and scroll down the output in one directiononly.You can scroll page by page or line by line.");
        cmd.add("> ---> This command Redirect the output to be written to afile using the redirect > create/replace file operator.");
        cmd.add(">>    --> This command Redirect the output to be written to afile using the redirect > create/appand to file operator.");
        cmd.add(" args ---> List all command arguments.");
        cmd.add("exit ---> Exit the Shell.");
        switch (flag) {
            case 0:
                for (int i = 0; i < cmd.size(); i++) {
                    System.out.println(cmd.get(i));
                }
                break;
            case 1:
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(path));
                for (int i = 0; i < cmd.size(); i++) {
                    writer.write(cmd.get(i));
                    writer.newLine();
                }
                writer.close();
                break;
            case 2:
                for (int i = 0; i < cmd.size(); i++) {
                    String temp = cmd.get(i) + System.lineSeparator();
                    Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
                }
                break;
            default:
                break;
        }
    }
}

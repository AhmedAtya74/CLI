/*


*/
import java.io.IOException;		
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Parser P=new Parser();
		Terminal T=new Terminal();
		Scanner data=new Scanner(System.in);
		while(true) {
		System.out.print(T.currentDirectory+"~");
		String command=data.nextLine();
		String[] all=command.split(" \\| ");
                // ls | pwd | cd path
		for(int i=0;i<all.length;i++) {
			if(P.parse(all[i])) {
				if(P.cmd.equals("clear")) {T.clear();}
				else if(P.cmd.equals("cd")) {T.currentDirectory = T.cd(T.currentDirectory, P.args[0]);}
				else if(P.cmd.equals("ls")) {T.ls(T.currentDirectory, P.args[1], P.flag);}
				else if(P.cmd.equals("cp")) {T.cp(P.args[0],P.args[1]);}
				else if(P.cmd.equals("mv")) {T.mv(P.args[0],P.args[1]);}
				else if(P.cmd.equals("rm")) {T.rm(P.args[0]);}
				else if(P.cmd.equals("mkdir")) {T.mkdir(T.currentDirectory+P.args[0]);}
				else if(P.cmd.equals("rmdir")) { T.rmdir(T.currentDirectory+P.args[0]);}
				else if(P.cmd.equals("cat")) {try {
					T.cat(P.args[0], P.args[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				else if(P.cmd.equals("more")) {T.more(P.args[0]);}
				else if(P.cmd.equals("pwd")) {try {
					T.pwd(P.args[1],P.flag);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				else if(P.cmd.equals("help")) {try {
					T.help(P.args[1], P.flag);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				else if(P.cmd.equals("args")) {try {
					T.args(P.args[0], P,P.args[2], P.flag);
				} catch (IOException e) {
					e.printStackTrace();
				}}
				else if(P.cmd.equals("date")) {try {
					T.gettingCurDate(P.args[1], P.flag);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				else if(P.cmd.equals("exit")) {System.exit(0);}
				
			}
			else {
				System.out.println("Wrong command ");
			}
		}
	}

	}
}

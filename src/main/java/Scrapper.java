import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Stack;

public  class Scrapper {

    private static String[] patterns;

    private static boolean wasC;
    private static boolean wasE;
    private static boolean wasW;
    private static  int[] wordMatching;

    Scrapper(String[] patterns, boolean wasW,boolean wasC, boolean wasE){
        this.patterns=patterns;
        this.wasW=wasW;
        this.wasC=wasC;
        this.wasE=wasE;
        this.wordMatching = new int[patterns.length];
    }

    private static void stringExtracting(String target, int index, String pattern){

        String[] splited = target.split(" ");

        for (int i = 0; i < splited.length ; i++) {

            if(splited[i].equals(pattern)){

                wordMatching[index]++;

                int j=i-1;
                Stack<String> line = new Stack<String>();
                while ((j!=-1) && !splited[j].endsWith(".")){
                    line.push(splited[j]);
                    j--;

                }
                line.push("\nSentence for matching word '"+pattern+"':\n");

                if(wasE)
                    while (line.size()!=0){
                        System.out.print(line.pop()+" ");
                    }

                j=i;

                while (!splited[j].endsWith(".") && !(splited.length-1==j)){
                    if(wasE)
                        System.out.print(splited[j]+" ");
                    j++;
                }

                i=j;


                if (wasE) {
                    System.out.print(splited[j] + " ");
                    System.out.println();
                    System.out.println();
                }
            }
        }
    }


    static void scrap(String url_) throws Exception {

        URL url = new URL(url_);
        System.out.println("\nSearching in URL:"+url);
        System.out.println("____________________________________________________________");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        int charCount =0;
        String inputLine;
        while ((inputLine = in.readLine()) != null) {

            if (inputLine.contains("<script>")){
                while (!inputLine.contains("</script>"))
                    inputLine += in.readLine();
            }

            if (inputLine.contains("<head>")){
                while (!inputLine.contains("</head>"))
                    inputLine = in.readLine();
            }

            if((inputLine.contains("<head>"))&&(inputLine.contains("</head>")))
                inputLine = in.readLine();


            if((inputLine.contains("<script>"))&&(inputLine.contains("</script>")))
                inputLine = in.readLine();

            if (inputLine.contains("<style>")){
                while (!inputLine.contains("</style>"))
                    inputLine = in.readLine();
            }
            if((inputLine.contains("<style>"))&&(inputLine.contains("</style>")))
                inputLine = in.readLine();

            if(inputLine.contains("<")&&!inputLine.contains(">"));
                while (!inputLine.contains(">"))
                    inputLine += in.readLine();


            String target = inputLine.replaceAll("<[^>]*>", " ");
            target = target.replaceAll("\\t", "");
            target = target.replaceAll("\\n", "");
            target = target.replaceAll("^ +", "");
            target = target.replaceAll("  +", " ");
            target = target.replaceAll(" ; *", "; ");
            target = target.replaceAll(" \\. *", ". ");
            target = target.replaceAll(" : *", ": ");
            target = target.replaceAll(" , *", ", ");

            charCount+=target.length();
            if(target.length()==0)
                continue;


            for (int i = 0; i < patterns.length; i++) {

                if(target.contains(patterns[i])){

                    stringExtracting(target,i,patterns[i]);

                }

            }

           // System.out.println(target); //<----Uncomment for printing all text on web page

        }

        if(wasW) {
            System.out.println("*************************************************************");
            System.out.println("Number of provided words occurrence on web page");
            for (int i = 0; i < patterns.length; i++) {
                System.out.println("    " + patterns[i] + ": " + wordMatching[i]);
                wordMatching[i] = 0;
            }
        }

        if (wasC)
            System.out.println("\nTotal characters: "+charCount+"");
        System.out.println("*************************************************************");

        in.close();
    }
}

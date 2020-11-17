import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyTest {
    static Random rand = new Random(1201483);

    public static void main(String[] args) {
        // Create User Pool
        List<TestUser> userPool = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            userPool.add(new TestUser(getRandString(7), getRandString(10)));
        }

        for (TestUser user : userPool) {
            String authFile = String.format("mytest/%s_auth.in", user.id);
            String outFile = String.format("mytest/%s_recommend.out", user.id);
            onTest("Recommend for " + user.id, authFile, "mytest/recommend.in", outFile, false);
        }

        // This operation may take a while.
        long startTime = System.nanoTime();
        onTest("Search",
                String.format("mytest/%s_auth.in", randChoose(userPool).id),
                "mytest/search.in",
                "mytest/search.out",
                true);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Search 수행 시간 (ms): "+ duration);
    }

    static void printOX(String prompt, boolean condition) {
        if (condition) {
            print(prompt + " : O");
        } else {
            print(prompt + " : X");
        }
    }

    static void print(Object o) {
        System.out.println(o);
    }

//    static void onTest(String testName, String authInput, String postInput, String output, boolean searchTest) {
//        UserInterface ui = new UserInterface();
//        BackEnd backend = new BackEnd();
//        FrontEnd frontEnd = new FrontEnd(ui, backend);
//        ui.createUITest(frontEnd, authInput, postInput);
//        ui.run();
//
//        try {
//            String content = Files.readString(Paths.get(output));
//            String content2 = (ui.authView.getOutput() + "\n" + ui.postView.getOutput());
//            // CRLF to LF
//            content = content.replaceAll("\r\n", "\n");
//            content2 = content2.replaceAll("\r\n", "\n");
//            printOX(testName, searchTest ? redacted(content2).equals(redacted(content)) : content2.equals(content));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    static void onTest(String testName, String authInput, String postInput, String output,boolean searchTest) {
        UserInterface ui = new UserInterface();
        BackEnd backend = new BackEnd();
        FrontEnd frontEnd = new FrontEnd(ui,backend);
        ui.createUITest(frontEnd, authInput, postInput);
        ui.run();

        try {
            String content = Files.readString(Paths.get(output)).replaceAll("\\s", "");
            String content2 = (ui.authView.getOutput() + ui.postView.getOutput()).replaceAll("\\s", "");
            printOX(testName, content2.equals(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String redacted(String text) {
        String ret = text.replaceAll("[a-zA-Z0-9]{7}@sns.com", "");
        ret = ret.replaceAll("id : [a-zA-Z0-9]{7}\\npasswd : .{10}", "");
        return ret;
    }

    static String getRandString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            switch (index) {
                case 0:
                    sb.append((char) (rand.nextInt(20) + 'a'));
                    break;
                case 1:
                    sb.append((char) (rand.nextInt(20) + 'A'));
                    break;
                case 2:
                    sb.append(rand.nextInt(10));
                    break;
            }
        }
        return sb.toString();
    }

    static <T> T randChoose(List<T> list) {
        return list.get(rand.nextInt(list.size()));
    }
}


class TestUser {
    String id;
    String pw;
    List<TestUser> friends = new ArrayList<>();

    public TestUser(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public void addFriend(TestUser friend) {
        if (!friends.contains(friend) && friend != this) {
            friends.add(friend);
        }
    }

    public String getFriends() {
        StringBuilder sb = new StringBuilder();
        for (TestUser friend : friends) {
            sb.append(friend.id);
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    public String getAuthInfo() {
        return String.format("%s\n%s\n", id, pw);
    }
}
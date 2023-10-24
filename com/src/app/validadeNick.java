package app;

public class validadeNick {
    public static void main(String[] args) {
        User user = new User();
        String nickname = user.getUserNick();


        boolean nickExists = existNick(user, nickname);
        if (nickExists) {
            System.out.println("O nickname existe.");
        } else {
            System.out.println("O nickname não existe.");
        }
    }

    public static boolean existNick(User user, String nickname) {
        String[] nomes = user.getUserNick().split(" ");
        for(String c : nomes) {
            if(user.nick.equals(nickname)) {
                return true;
            }
        }
        return false;
    }

}

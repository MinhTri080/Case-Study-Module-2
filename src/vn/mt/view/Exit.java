package vn.mt.view;

public class Exit implements Runnable{
    @Override
    public void run() {
        String[] string = {
                "┌-------------------------------------┐",
                "|        Đóng cửa thả chó!!!          |",
                "|-------------------------------------|",
                "|                                     |",
                "|             Về Nhà Đi Ku            |",
                "|                                     |",
                "└-------------------------------------┘",
        };
        for (int i = 0; i < string.length; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(string[i]);
        }
        System.exit(0);
        return;
    }
}

package controller;

public class SetTime extends Thread {
    private boolean running = true; // 외부에서 제어할 수 있는 플래그

    @Override
    public void run() {
        int setTime = 70;
        while (running) {
            setTime -= 10;
            System.out.print("\r 발주 넣을 수 있는 남은시간 " + setTime + "초 입니다.");
            if (setTime <= 0) {
                running = false;
                System.out.print("\n준비시간이 종료되었습니다. 엔터를 눌러 가게를 열어주세요.");
            }
            try {
                Thread.sleep(10000); // 5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stopRunning() {
        running = false;
    }
    public boolean isRunning() {
        return running;
    }
}

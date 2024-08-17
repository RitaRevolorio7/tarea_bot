package tarea_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            tarea_Bot.tareaBot bot = new tarea_Bot.tareaBot();
            botsApi.registerBot(bot);
            System.out.println("El Bot está funcionando......");
        } catch (Exception var3) {
            Exception ex = var3;
            System.out.println("error" + ex.getMessage());
        }

    }
}

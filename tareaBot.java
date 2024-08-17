package tarea_Bot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tarea_bot.tarea_bot;

public class tareaBot extends TelegramLongPollingBot {
    private final String nombre = "Rita Revolorio";
    private final String carnet = "0905-21-18066";
    private final String semestre = "4to Semestre";


    public String getBotUsername() {
        return "rita_rg_7_bot";
    }

    public String getBotToken() {
        return "7241139532:AAHAna06uKEt9wCnlG7M-SonMr0hp-9nVC4";
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String userName;
            if (message_text.equalsIgnoreCase("/info")) {
                userName = "Información personal:\nNombre: Rita Revolorio\nCarnet: 0905-21-18066\nSemestre: 4to Semestre";
                this.sendText(chat_id, userName);
            } else if (message_text.equalsIgnoreCase("/progra")) {
                this.sendText(chat_id, "Una curso muy completo en donde puedo implementar muchas funciones");
            } else if (message_text.equalsIgnoreCase("/hola")) {
                userName = update.getMessage().getFrom().getFirstName();
                String fechaActual = (new SimpleDateFormat("EEEE dd 'de' MMMM")).format(new Date());
                this.sendText(chat_id, "hola, " + userName + ", hoy es " + fechaActual);
            } else if (message_text.startsWith("/cambio")) {
                try {
                    String[] partes = message_text.split(" ");
                    double euros = Double.parseDouble(partes[1]);
                    double tipoDeCambio = 8.9;
                    double quetzales = euros * tipoDeCambio;
                    this.sendText(chat_id, "" + euros + " Euros Son " + String.format("%.2f", quetzales) + " quetzales.");
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException var12) {
                    this.sendText(chat_id, "Por favor, usa el formato /cambio [cantidad_en_euros]. Ejemplo: /cambio 897");
                }
            } else {
                this.sendText(chat_id, "Comando no reconocido. Por favor, usa uno de los comandos válidos como /info, /progra, /hola o /cambio.");
            }
        }

    }

    private void sendText(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            this.execute(message);
        } catch (TelegramApiException var6) {
            TelegramApiException e = var6;
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            tarea_bot bot = new tarea_bot();
            botsApi.registerBot((LongPollingBot) bot);
            System.out.println("El Bot está funcionando..................");
        } catch (Exception var3) {
            Exception ex = var3;
            System.out.println("error" + ex.getMessage());
        }

    }
}

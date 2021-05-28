package com.economigos.economigostelegram;

import com.economigos.economigostelegram.bot.BotTelegram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EconomigosTelegramApplication {

	public static void main(String[] args) {
		BotTelegram.main(args);
		SpringApplication.run(EconomigosTelegramApplication.class, args);
	}

}

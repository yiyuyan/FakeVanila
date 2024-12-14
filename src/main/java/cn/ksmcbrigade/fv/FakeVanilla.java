package cn.ksmcbrigade.fv;

import cn.ksmcbrigade.fv.config.Config;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

import java.io.IOException;

public class FakeVanilla implements ClientModInitializer {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Config config;

    static {
        try {
            config = new Config("config/fv-config.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Fake Vanilla mod loaded.");
    }
}

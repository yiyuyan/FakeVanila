package cn.ksmcbrigade.fv.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Config {

    private final File file;
    private boolean enabled = false;

    public Config(String file) throws IOException {
        this.file = new File(file);
        this.reload();
    }

    public void set(boolean enabled) throws IOException {
        this.enabled = enabled;
        this.save(true);
    }

    public boolean get(){
        return this.enabled;
    }

    public void save(boolean ex) throws IOException {
        if(!file.exists() || ex){
            JsonObject object = new JsonObject();
            object.addProperty("enabled",enabled);
            FileUtils.writeStringToFile(this.file, object.toString());
        }
    }

    public void reload() throws IOException {
        if(!this.file.exists()){
            this.save(false);
        }
        this.enabled = JsonParser.parseString(FileUtils.readFileToString(this.file)).getAsJsonObject().get("enabled").getAsBoolean();
    }
}

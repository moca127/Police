package kr.mocha;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import kr.mocha.command.AddPoliceCommand;
import kr.mocha.command.DelPoliceCommand;
import kr.mocha.command.PoliceCommand;

public class Police extends PluginBase implements Listener{
    public static Police instance;
	public Config police;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdirs();
		police = new Config(getDataFolder()+"/police.yml",Config.YAML);
		this.getServer().getCommandMap().register("addpolice", new AddPoliceCommand());
		this.getServer().getCommandMap().register("delpolice", new DelPoliceCommand());
		this.getServer().getCommandMap().register("police", new PoliceCommand());
		save();
		super.onEnable();
	}
	@Override
	public void onDisable() {
        super.onDisable();
		save();
	}
	public void save(){
		police.save();
	}
	public static Police getInstance(){
        return instance;
    }
}
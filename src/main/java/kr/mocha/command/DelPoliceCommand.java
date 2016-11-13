package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.Police;
import kr.mocha.manager.PoliceManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class DelPoliceCommand extends Command{
    public PoliceManager manager = new PoliceManager();

    public DelPoliceCommand(){
        super("delpolice", "경찰을 삭제합니다.", "/delpolice <player>", new String[]{"policedel", "dp", "경찰삭제"});
        this.setPermission("policeManager.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            try{
                if(manager.isPolice(args[0])){
                    manager.delPolice(args[0]);
                    Police.getInstance().getServer().broadcastMessage(
                            TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+args[0]+"(이)가 경찰에서 삭제되었습니다.");
                    return true;
                }else sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"경찰이 아닙니다.");
            }catch (ArrayIndexOutOfBoundsException e){
                sender.sendMessage(TextFormat.RED+this.getUsage());
            }
        }
        return false;
    }
}

package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.Police;
import kr.mocha.manager.PoliceManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class AddPoliceCommand extends Command{
    public PoliceManager manager = new PoliceManager();

    public AddPoliceCommand(){
        super("addpolice", "경찰을 추가합니다.", "/addpolice <player>", new String[]{"policeadd", "ap", "경찰추가"});
        this.setPermission("policeManager.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            try{
                if(manager.addPolice(args[0])){
                    Police.getInstance().getServer().broadcastMessage(
                            TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+args[0]+"(이)가 경찰로 임명되었습니다.");
                    return true;
                }else sender.sendMessage(TextFormat.RED+"오류가 발생하였습니다.");
            }catch (ArrayIndexOutOfBoundsException e){
                sender.sendMessage(TextFormat.RED+this.getUsage());
            }
        }
        return false;
    }
}

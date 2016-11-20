package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import kr.mocha.Police;
import kr.mocha.manager.PoliceManager;

/**
 * Created by mocha on 16. 11. 5.
 */
public class PoliceCommand extends Command{
    public PoliceManager manager = new PoliceManager();
    public Server server = Police.getInstance().getServer();

    public PoliceCommand(){
        super("police", "경찰명령어", "/police <ban|unban|kick|say|tp>");
        this.setPermission("police.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!manager.isPolice(sender.getName()))
            sender.sendMessage(TextFormat.RED + "명령어의 권한이 없습니다.");
        else {
            try {
                switch (args[0]) {
                    case "ban":
                        try {
                            server.getNameBans().addBan(args[1]);
                            server.broadcastMessage(
                                    TextFormat.GOLD + args[1] + "이 " + sender.getName() + "에 의해 밴되었습니다.");
                        } catch (NullPointerException e) {
                            sender.sendMessage(TextFormat.RED + "플레이어를 찾을 수 없습니다.");
                        }
                        break;
                    case "unban":
                    case "pardon":
                        try {
                            server.getNameBans().remove(args[1]);
                            server.broadcastMessage(
                                    TextFormat.GOLD + args[1] + "이 " + sender.getName() + "에 의해 언밴되었습니다.");
                        } catch (Exception e) {
                            sender.sendMessage(TextFormat.RED + "플레이어를 찾을 수 없습니다.");
                        }
                        break;
                    case "kick":
                        try {
                            Player player = server.getPlayer(args[1]);
                            player.kick(TextFormat.RED + sender.getName() + "에 의해 킥되었습니다.");
                            server.broadcastMessage(
                                    TextFormat.GOLD + player.getName() + "이 " + sender.getName() + "에 의해 킥되었습니다.");
                        } catch (NullPointerException e) {
                            sender.sendMessage(TextFormat.RED + "플레이어를 찾을 수 없습니다.");
                        }
                        break;
                    case "say":
                        server.broadcastMessage(TextFormat.LIGHT_PURPLE +"["+sender.getName()+"] "+arrayToString(args));
                        break;
                    case "tp":
                        try {
                            Location loc = server.getPlayer(args[1]).getLocation();
                            Player player = (Player)sender;
                            player.teleport(loc);
                            player.sendMessage(TextFormat.GOLD + player.getName() + "에게로 이동하였습니다.");
                        } catch (NullPointerException e) {
                            sender.sendMessage(TextFormat.RED + "플레이어를 찾을 수 없습니다.");
                        } catch (ClassCastException e){
                            sender.sendMessage(TextFormat.RED+"콘솔 사용불가!");
                        }
                        break;
                    default:
                        sender.sendMessage(TextFormat.RED + this.getUsage());
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(TextFormat.RED + this.getUsage());
            }
        }
        return false;
    }
    private String arrayToString(String[] args){
        String result = "";
        for(int i=0;i<args.length-1;i++)
            result += args[i+1]+" ";
        return result;
    }
}

package kr.mocha.manager;

import cn.nukkit.utils.Config;
import kr.mocha.Police;

/**
 * Created by mocha on 16. 11. 6.
 */
public class PoliceManager {
    public Config police = Police.getInstance().police;

    public boolean addPolice(String player){
        try {
            police.set(player, true);
            police.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean delPolice(String player){
        try{
            police.remove(player);
            police.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean isPolice(String player){
        return police.exists(player);
    }
}

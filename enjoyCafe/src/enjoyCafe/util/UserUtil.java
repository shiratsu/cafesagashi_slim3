package enjoyCafe.util;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserUtil {
    
    public static boolean checkUser(UserService userService){
        String userName = null;
        User user = userService.getCurrentUser();
        if(user != null){
            if(user instanceof Object){
                userName = user.toString();
                
                //３人以外ならフォルス
                if(!"shiratsu2009@gmail.com".equals(userName) &&
                   !"mfujii@gmail.com".equals(userName) &&
                   !"nabekorock@gmail.com".equals(userName)
                   ){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }
}

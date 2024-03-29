package mypkg.utility;

import mypkg.common.SuperController;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Myutility {
    // todolist.txt 파일을 읽어서
    // 스트림을 이용하여 Properties 객체에 데이터를 추가합니다.
    public static Map<String, SuperController> getActionMapList(String todolist){
        Properties prop = new Properties() ;
        FileInputStream fis = null ;

        Map<String, SuperController> mapdata = new HashMap<String, SuperController>() ;

        try {
            fis = new FileInputStream(todolist) ;
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null) {fis.close();}
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        // 현재까지 커맨드 이름을 key로 해당 클래스 문자열을 value로 저장해둔 상태입니다.
        System.out.println("prop.toString()");
        System.out.println(prop.toString());

        Enumeration<Object> enu = prop.keys() ;

        while(enu.hasMoreElements()) {
            String command = enu.nextElement().toString() ;
            // className는 아직 문자열 상태
            String className = prop.getProperty(command) ;
            try {
                // forName 메소드는 문자열을 읽어서 instance으로 만들어 줍니다.
                Class<?> handleClass = Class.forName(className) ;
                System.out.println(handleClass);
                //모든 서브 컨트롤러들을 SuperController 형태로 승급을 합니다.
                SuperController instance = (SuperController)handleClass.newInstance();

                mapdata.put(command, instance) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("mapdata.toString");
        System.out.println(mapdata.toString());

        return mapdata ;
    }
}




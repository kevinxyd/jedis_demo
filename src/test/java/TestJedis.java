import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TestJedis {


    Jedis jedis;

    @Before
    public void before(){
        jedis= new Jedis("localhost",6379);
        //密码认值
        jedis.auth("123456");
    }

    @After
    public void after (){
        jedis.close();
    }

    /**
     * 测试连接
     */
    @Test
    public void test1(){

        System.out.println(jedis.echo("hi"));
        System.out.println(jedis.ping());
        System.out.println(jedis.info());

    }

//key
    @Test
    public void test2(){
//        jedis.select(1);
        System.out.println(jedis.keys("*"));
    }

    //string类型
    @Test
    public void test3(){
        jedis.set("jedis","jedisVal");
        System.out.println(jedis.get("jedis"));
        System.out.println(jedis.strlen("jedis"));
    }

    //list类型
    @Test
    public void test4(){
        jedis.lpush("list","a","b");
        System.out.println(jedis.lrange("list",0,-1));
    }

    //set
    @Test
    public void test5(){
        jedis.sadd("set","手提电脑","平板电脑","手机");
        Set<String> set = jedis.smembers("set");
        for (String s : set) {
            System.out.println(s);
        }
    }

    //hash
    @Test
    public  void test6(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name","糖醋鱼");
        map.put("price","38");
        jedis.hmset("food:1",map);
        List<String> hvals = jedis.hvals("food:1");
        for (String hval : hvals) {
            System.out.println(hval);
        }
    }

    //sortedset
    @Test
    public void test7(){
        jedis.zadd("musics", 20010, "1967");
        jedis.zadd("musics", 16555, "我在人民广场吃着炸鸡");
        jedis.zadd("musics", 18867, "小跳蛙");

        Set<String> musics = jedis.zrevrange("musics", 0, -1);
        System.out.println(musics);


    }
}

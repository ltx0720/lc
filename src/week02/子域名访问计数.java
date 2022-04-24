package week02;

import java.util.*;

public class 子域名访问计数 {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        Arrays.stream(cpdomains).forEach(s -> subdomainVisits(s, map));

        List<String> list = new ArrayList(map.size());
        map.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String res = value + " " + key;
            list.add(res);
        });

        return list;
    }


    private void subdomainVisits(String word, Map<String, Integer> map) {
        String[] words = word.split(" ");
        int count = Integer.parseInt(words[0]);
        String[] strings = words[1].split("\\.");
        String str = "";
        for (int i=strings.length-1; i>=0; i--) {
            if ("".equals(str)) {
                str = strings[i];
            } else {
                str = strings[i] + "." + str;
            }
            Integer c = map.get(str);
            map.put(str, c != null ? c+count : count);
        }
    }


    public static void main(String[] args) {

        子域名访问计数 t811 = new 子域名访问计数();
        String[] strings = new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> strings1 = t811.subdomainVisits(strings);
        System.out.println(strings1);
    }

}

package week04;

import java.util.HashMap;
import java.util.Map;

public class 在线选举 {
    private int[] res;

    private int[] times;

    public 在线选举(int[] persons, int[] times) {
        this.res = new int[times.length];
        this.times = times;
        Map<Integer, Integer> map = new HashMap<>();
        int maxTicket = 0;
        int maxTicketPerson = persons[0];

        for (int i=0; i<times.length; i++) {
            int person = persons[i];
            int ticket = 1;
            if (map.containsKey(person)) {
                ticket = map.get(person) + 1;
            }
            map.put(person, ticket);
            if (ticket >= maxTicket) {
                maxTicketPerson = person;
                maxTicket = ticket;
            }
            res[i] = maxTicketPerson;
        }
    }

    public int q(int t) {
        int left = 0, right = this.times.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (t < this.times[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return res[left];
    }
}

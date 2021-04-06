package com.examination.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词搜寻中的DFA算法
 *
 * @author Meimengling
 * @date 2021-4-6 11:30
 */
public class Main {

    private Map<String, Map> sensitiveWordMap;

    public static void main(String[] args) {
        Set<String> keyWordSet = new HashSet<>();
        keyWordSet.add("王八蛋");
        keyWordSet.add("王八羔子");

        Main main = new Main();

        main.addSensitiveWordToHashMap(keyWordSet);

        int result = main.checkSensitiveWord("骂人的话王八羔子是不对的", 0, 1);

        System.out.println(result);
    }

    // 构造敏感词实现代码
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap<>(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWordMap = null;

        Iterator<String> iterator = keyWordSet.iterator();

        while (iterator.hasNext()) {
            key = iterator.next();
            nowMap = sensitiveWordMap;

            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);

                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    newWordMap = new HashMap<>();
                    newWordMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWordMap);
                    nowMap = newWordMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    // 实现敏感词查询代码
    private int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;

        char word = 0;
        Map nowMap = sensitiveWordMap;

        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag ++;
                if ("1".equals(nowMap.get("isEnd"))) {
                    flag = true;
                    if (/*minMatchType*/ 1 == matchType) {
                        break;
                    }
                }
            } else {
                nowMap = sensitiveWordMap;
//                break;
            }
        }
        if (matchFlag < 2 || !flag) {
            matchFlag = 0;
        }

        return matchFlag;
    }

}

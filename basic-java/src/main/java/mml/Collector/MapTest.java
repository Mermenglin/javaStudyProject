package Collector;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-5-26 15:48
 */
public class MapTest {

    public static void main(String[] args) {
        List<SceneAction> sceneActions = new ArrayList<>();
        Map<Long, List<SceneAction>> sceneActionMap = sceneActions.stream()
                .collect(Collectors.groupingBy(SceneAction::getScene_id));
        System.out.println(sceneActionMap);
    }

    @Test
    public void test() {
        Map<String, List<String>> map = new HashMap();
        map.computeIfAbsent("java", key -> new ArrayList<>()).add("map");

        map.computeIfPresent("java1", (key, value) -> {
            value.add("spring");
            return value;
        });

        System.out.println(map);

        Map<String, String> map2 = new HashMap();

        System.out.println(map2.getOrDefault("java2", "value not exist"));
    }

    class SceneAction {
        private Long id;
        private Integer app_id;
        private Long homegroup_id;
        private Long scene_id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getApp_id() {
            return app_id;
        }

        public void setApp_id(Integer app_id) {
            this.app_id = app_id;
        }

        public Long getHomegroup_id() {
            return homegroup_id;
        }

        public void setHomegroup_id(Long homegroup_id) {
            this.homegroup_id = homegroup_id;
        }

        public Long getScene_id() {
            return scene_id;
        }

        public void setScene_id(Long scene_id) {
            this.scene_id = scene_id;
        }
    }

}

package com.gustz.dove.mpcli.api.account.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 二维码信息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class QrcodeInfo extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 场景值
     */
    @JsonProperty("scene")
    private Scene scene;

    public QrcodeInfo(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static class Scene {

        /**
         * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
         */
        @JsonProperty("scene_id")
        private int sceneId;

        /**
         * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
         */
        @JsonProperty("scene_str")
        private String sceneStr;

        public Scene(int sceneId) {
            this.sceneId = sceneId;
        }

        public int getSceneId() {
            return sceneId;
        }

        public void setSceneId(int sceneId) {
            this.sceneId = sceneId;
        }

        public String getSceneStr() {
            return sceneStr;
        }

        public void setSceneStr(String sceneStr) {
            this.sceneStr = sceneStr;
        }
    }
}

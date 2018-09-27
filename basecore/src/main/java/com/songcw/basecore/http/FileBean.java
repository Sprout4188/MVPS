package com.songcw.basecore.http;

import java.util.List;

/**
 * Created by Sprout on 2018/9/25
 */
public class FileBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * file1 : http://www.baidu.com
         * file2 : http://www.baidu.com
         */

        private String file1;
        private String file2;

        public String getFile1() {
            return file1;
        }

        public void setFile1(String file1) {
            this.file1 = file1;
        }

        public String getFile2() {
            return file2;
        }

        public void setFile2(String file2) {
            this.file2 = file2;
        }
    }
}

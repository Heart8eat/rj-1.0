package com.rj.backendjixian.util;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileUtilTest {

    @Test
    void deleteFile() {
        File file = FileUtil.file("../../upload/3c63510cf0164a07bbea0164b5d8ea5d");
        com.rj.backendjixian.util.FileUtil.deleteFile(file);
    }
}
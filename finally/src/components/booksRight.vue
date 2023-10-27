<template>
    <div class="manage">

        <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="收货人" prop="name">
                    <el-input placeholder="请输入名称" v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="详细地址" prop="address">
                    <el-input placeholder="请输入地址" v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phnumber">
                    <el-input placeholder="请输入手机号码" v-model="form.phnumber"></el-input>
                </el-form-item>
                <el-form-item label="邮箱地址" prop="mail">
                    <el-input placeholder="请输入邮箱" v-model="form.mail"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default{
    data() {
      return {
        dialogVisible: false,
        form:{
            name:'',
            address:'',
            phnumber:'',
            mail:''
        }
      };
    },

}
</script>

<template>
  <el-button type="text" @click="outerVisible = true">open the outer Dialog</el-button>
  <el-dialog title="Outer Dialog" v-model="outerVisible">
    <template #default>
      <el-dialog
          width="30%"
          title="Inner Dialog"
          v-model="innerVisible"
          append-to-body>
      </el-dialog>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="outerVisible = false">Cancel</el-button>
        <el-button type="primary" @click="innerVisible = true">open the inner Dialog</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script>
  export default {
    data() {
      return {
        outerVisible: false,
        innerVisible: false
      };
    }
  }
</script>
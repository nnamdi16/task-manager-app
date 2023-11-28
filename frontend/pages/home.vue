<template>
  <Title>Todo App | Todo List</Title>
  <Meta name="description" content="Make and Manage your todos here" />
  <TaskInput />
  <TaskList />
</template>

<script setup lang="ts">

onNuxtReady(async () => {
  try {
      await navigateTo("/home");

  } catch (err) {
    console.error(err)
  }
});
</script>

<style lang="scss" scoped></style>

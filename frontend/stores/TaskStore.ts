import { defineStore } from "pinia";
import  type {Task} from "../models/todoModel";

export type RootState = {
  tasks: Task[];
  apiLink: string;
};
export const useTaskStore = defineStore("taskStore", {
  state: (): RootState => ({
    tasks: [],
    apiLink: useRuntimeConfig().public.Todos_URL,
  }),

  getters: {
    allTasks(): Task[] {
      return this.tasks;
    },
    completedTasks(): Task[] {
      return this.tasks.filter((task) => task.completed);
    },
    leftTasks(): Task[] {
      return this.tasks.filter((task) => !task.completed);
    },
  },
  actions: {
    async fetchTasks() {
      const {data} = await $fetch(this.apiLink) as any;
      this.tasks = data as unknown as Task []
    },

    async addTask(content: string) {
      await $fetch(this.apiLink, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ content }),
      }).catch((err) => {
        console.error(err.data.errors);
      });
      await this.fetchTasks();
    },
    async completeTask(id: string) {
      const {data} = await $fetch(`${this.apiLink}/${id}`, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ completed: true }),
      }).catch((err) => {
        console.error(err.data.errors);
      });
      return data;
    },
    async clearCompleted() {
      this.completedTasks.forEach(
        async (task) => await this.completeTask(task.id)
      );
      await this.fetchTasks();
    },
    async toggleFinished(id: string) {
      const response =  await this.completeTask(id) as Task;
      const task = this.tasks.find((t) => t.id === id) as Task;
      task.completed = response.completed;
    },
  },
});

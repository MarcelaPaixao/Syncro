<template>
  <div class="w-full space-y-2">
    <input
      ref="inputRef"
      :type="type"
      v-model="newItem"
      @keydown.enter.prevent="addItem"
      :placeholder="placeholder"
      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500"
    />

    <div
      v-if="modelValue && modelValue.length > 0"
      class="w-full space-y-2 p-3 max-h-[300px] overflow-y-auto"
    >
      <div class="space-y-1">
        <div
          v-for="(item, index) in modelValue"
          :key="index"
          class="w-full px-3 py-2 rounded-lg bg-gray-100 text-gray-700 flex justify-between items-center"
        >
          <a
            v-if="itemsAsLinks"
            :href="item"
            target="_blank"
            rel="noopener noreferrer"
            class="font-medium hover:underline hover:text-gray-500 flex-grow truncate"
          >
            {{ item }}
          </a>
          <span v-else class="font-medium flex-grow truncate">
            {{ item }}
          </span>
          <button
            @click.stop="removeItem(index)"
            class="cursor-pointer font-bold text-gray-600 hover:text-gray-400"
          >
            &times;
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  modelValue: { type: Array, required: true },
  type: { type: String, default: "text" },
  placeholder: { type: String, default: "Adicione e pressioe Enter" },
  itemsAsLinks: { type: Boolean, default: false },
});

const emits = defineEmits(["update:modelValue", "interaction"]);

const newItem = ref("");
const inputRef = ref(null);

function addItem() {
  const valueToAdd = newItem.value.trim();

  if (valueToAdd && !props.modelValue.includes(valueToAdd)) {
    const newItems = [...props.modelValue, valueToAdd];
    emits("update:modelValue", newItems);
  }
  newItem.value = "";
}

function removeItem(index) {
  const newItems = props.modelValue.filter((_, idx) => idx !== index);
  emits("update:modelValue", newItems);
}

watch(newItem, (newValue) => {
  if (newValue.length > 0) {
    emits("interaction");
  }
});
</script>

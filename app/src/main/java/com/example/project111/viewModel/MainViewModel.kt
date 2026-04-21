package com.example.project111.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project111.repository.MainRepository
import com.example.project111.domain.CategoryModel
import com.example.project111.domain.FoodModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    val categories: LiveData<MutableList<CategoryModel>> = repository.loadCategory()
    val bestFoods: LiveData<MutableList<FoodModel>> = repository.loadBestFood()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        return repository.loadBestFood()
    }

    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }
    private val _selectedFood = MutableStateFlow<FoodModel?>(null)
    val selectedFood: StateFlow<FoodModel?> = _selectedFood


    fun selectedFood(foodModel: FoodModel?){
        _selectedFood.value = foodModel
    }
}
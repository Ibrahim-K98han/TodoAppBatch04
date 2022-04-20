package com.tanvir.training.todoappbatch04

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanvir.training.todoappbatch04.adapters.TodoAdapter
import com.tanvir.training.todoappbatch04.databinding.FragmentTodoListBinding
import com.tanvir.training.todoappbatch04.prefdata.LoginPreference
import com.tanvir.training.todoappbatch04.prefdata.isEven
import com.tanvir.training.todoappbatch04.viewmodels.TodoViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var preference: LoginPreference
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.todo_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_logout -> {
                lifecycle.coroutineScope.launch {
                    preference.setLoginStatus(status = false, 0L, requireContext())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        preference = LoginPreference(requireContext())
        val adapter = TodoAdapter{model, action ->

        }
        binding.todoRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.todoRV.adapter = adapter
        preference.isLoggedInFlow
            .asLiveData()
            .observe(viewLifecycleOwner){
            if (!it) {
                findNavController().navigate(R.id.action_todoListFragment_to_loginFragment)
            }
        }

        preference.userIdFlow.asLiveData()
            .observe(viewLifecycleOwner) {
                todoViewModel.getTodoByUserId(it).observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }


        binding.newTodoFab.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_newTodoFragment)
        }
        return binding.root
    }
}
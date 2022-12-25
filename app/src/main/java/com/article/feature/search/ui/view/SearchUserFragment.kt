package com.article.feature.search.ui.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentSearchUserBinding
import com.article.feature.search.ui.adapter.AdapterSearchUser
import com.article.feature.search.ui.viewmodel.SearchViewModel


class SearchUserFragment : Fragment() {

    private lateinit var binding: FragmentSearchUserBinding
    private val viewModel: SearchViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = AdapterSearchUser {
            safeNavigate(SearchFragmentDirections.actionSearchFragmentToProfileFragment(it.id))
        }

        binding.recyclerViewSearchUserFragment.adapter = adapter

        viewModel.searchResponse.observe(viewLifecycleOwner) { response ->
            val resultList = response?.users
            if (!resultList.isNullOrEmpty()) {
                binding.tvNotFoundSearchUserFragment.visibility = View.GONE
                adapter.submitList(resultList.map { it.toItemUserView() })
            } else {
                adapter.submitList(null)
                binding.tvNotFoundSearchUserFragment.visibility = View.VISIBLE
            }
        }
    }
}
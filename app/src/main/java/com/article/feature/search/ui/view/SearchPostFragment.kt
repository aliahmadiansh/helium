package com.article.feature.search.ui.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentSearchPostBinding
import com.article.feature.search.ui.adapter.AdapterSearchPost
import com.article.feature.search.ui.viewmodel.SearchViewModel

class SearchPostFragment : Fragment() {

    private lateinit var binding: FragmentSearchPostBinding
    private val viewModel: SearchViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = AdapterSearchPost {
            safeNavigate(
                SearchFragmentDirections.actionSearchFragmentToDetailArticleFragment(it.id)
            )
        }

        binding.recyclerViewSearchPostFragment.adapter = adapter

        viewModel.searchResponse.observe(viewLifecycleOwner) { response ->
            val resultList = response?.articles
            if (!resultList.isNullOrEmpty()) {
                binding.tvNotFoundSearchPostFragment.visibility = View.GONE
                adapter.submitList(resultList.map { it.toItemArticleView() })
            } else {
                adapter.submitList(null)
                binding.tvNotFoundSearchPostFragment.visibility = View.VISIBLE
            }
        }
    }
}
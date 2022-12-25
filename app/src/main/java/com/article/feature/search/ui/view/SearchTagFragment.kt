package com.article.feature.search.ui.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.article.databinding.FragmentSearchTagBinding
import com.article.feature.search.ui.viewmodel.SearchViewModel
import com.google.android.material.chip.Chip

class SearchTagFragment : Fragment() {

    private lateinit var binding: FragmentSearchTagBinding
    private val viewModel: SearchViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchTagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.searchResponse.observe(viewLifecycleOwner) { response ->
            val resultList = response?.tags
            if (!resultList.isNullOrEmpty()) {
                binding.tvNotFoundSearchTagFragment.visibility = View.GONE
                resultList.forEach { item ->
                    val chip = Chip(requireContext())
                    chip.id = item.id
                    chip.text = item.name
                    binding.chipGroupSearchTagFragment.addView(chip)
                }
            } else {
                binding.chipGroupSearchTagFragment.removeAllViews()
                binding.tvNotFoundSearchTagFragment.visibility = View.VISIBLE
            }
        }
    }
}
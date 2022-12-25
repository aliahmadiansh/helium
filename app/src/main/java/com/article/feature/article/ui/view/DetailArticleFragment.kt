package com.article.feature.article.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.article.R
import com.article.core.common.appSnackBar
import com.article.databinding.FragmentArticleDetailBinding
import com.article.feature.article.ui.viewmodel.DetailArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailArticleFragment : Fragment() {

    private val viewModel: DetailArticleViewModel by viewModels()
    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        binding.detailArticleViewModel = viewModel
        binding.lifecycleOwner = this
        binding.ivBackArticleDetailFragment.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObserve() {
        viewModel.snackBarMessage.observe(viewLifecycleOwner) { list ->
            val message = buildString {
                list.forEach {
                    if (this.isEmpty())
                        this.append(it)
                    else
                        this.append("\n$it")
                }
            }
            requireView().appSnackBar(message)
        }

        viewModel.snackBarBookmark.observe(viewLifecycleOwner) {
            if (it) {
                requireView().appSnackBar(resources.getString(R.string.add_to_bookmark))
            }else {
                requireView().appSnackBar(resources.getString(R.string.remove_from_bookmark))
            }
        }
    }
}
package com.article.feature.article.ui.view

import PublishTagBottomSheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.article.R
import com.article.core.common.appSnackBar
import com.article.databinding.FragmentPublishArticleBinding
import com.article.feature.article.ui.viewmodel.PublishArticleViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishArticleFragment : Fragment() {

    private lateinit var binding: FragmentPublishArticleBinding
    private val viewModel: PublishArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublishArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

        initClickListener()
    }

    private fun initClickListener() {
        binding.ivClosePublishFragment.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.chipAddTagPublishArticleFragment.setOnClickListener {
            PublishTagBottomSheet().show(
                childFragmentManager,
                HomeTagBottomSheetFragment.TAG
            )

        }
        binding.btnPublishPublishArticleFragment.setOnClickListener {
            if (!viewModel.isEmptyValue()) {
                binding.progressPublishArticleFragment.visibility = View.VISIBLE
                binding.btnPublishPublishArticleFragment.visibility = View.INVISIBLE
                viewModel.publishArticle()
            } else {
                requireView().appSnackBar(resources.getString(R.string.error_empty_input))
            }
        }
    }

    private fun initObserver() {
        binding.publishArticleViewModel = viewModel
        viewModel.successPublish.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigateUp()
            } else {
                binding.btnPublishPublishArticleFragment.visibility = View.VISIBLE
                binding.progressPublishArticleFragment.visibility = View.GONE
            }
        }

        viewModel.tagModel.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.chipSelectedPublishArticleFragment.visibility = View.VISIBLE
                binding.chipSelectedPublishArticleFragment.text = it.name
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { list ->
            binding.btnPublishPublishArticleFragment.visibility = View.VISIBLE
            binding.progressPublishArticleFragment.visibility = View.GONE
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

    }
}
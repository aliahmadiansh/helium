package com.article.feature.profile.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentRecyclerArticleBinding
import com.article.feature.profile.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyArticleFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels({ requireParentFragment() })
    private lateinit var binding: FragmentRecyclerArticleBinding
    private lateinit var myArticleAdapter: MyArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        myArticleAdapter = MyArticleAdapter(onItemArticleClickListener = {
            safeNavigate(
                ProfileFragmentDirections.actionProfileFragmentToDetailArticleFragment(it.id)
            )
        })

        binding.rvProfileArticleFragment.apply {
            adapter = myArticleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initObserve() {
        viewModel.articleList.observe(viewLifecycleOwner) {
            if (it != null) {
                myArticleAdapter.submitList(it)
            }

        }
    }
}
package com.kontrakanprojects.al_kisah25nabidanrosul.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.al_kisah25nabidanrosul.R
import com.kontrakanprojects.al_kisah25nabidanrosul.databinding.FragmentListKisahNabiBinding
import com.kontrakanprojects.al_kisah25nabidanrosul.main.MainViewModel
import com.kontrakanprojects.al_kisah25nabidanrosul.main.adapter.MainAdapter
import com.kontrakanprojects.al_kisah25nabidanrosul.model.ResultsKisahNabi
import com.kontrakanprojects.al_kisah25nabidanrosul.utils.showMessage
import www.sanju.motiontoast.MotionToast

class ListKisahNabiFragment : Fragment() {

    private var _binding: FragmentListKisahNabiBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListKisahNabiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observe()
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter()
        with(binding.rvKisahNabi) {
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        mainAdapter.setOnItemClickCallBack(object : MainAdapter.OnItemClickCallBack {
            override fun onItemClicked(resultsKisahNabi: ResultsKisahNabi) {
                findNavController().navigate(
                    ListKisahNabiFragmentDirections.actionListKisahNabiFragmentToDetailKisahNabiFragment(
                        resultsKisahNabi
                    )
                )
            }
        })
    }

    private fun observe() {
        isLoading(true)
        viewModel.getKisahNabi().observe(viewLifecycleOwner, { response ->
            isLoading(false)
            if (response != null) {
                val result = response.result
                mainAdapter.setData(result)
            } else {
                showMessage(
                    requireActivity(),
                    getString(R.string.failed),
                    style = MotionToast.TOAST_ERROR
                )
            }
        })
    }

    private fun isLoading(status: Boolean) {
        with(binding) {
            if (status) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
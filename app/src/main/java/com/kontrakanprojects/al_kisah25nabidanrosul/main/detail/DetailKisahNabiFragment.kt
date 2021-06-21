package com.kontrakanprojects.al_kisah25nabidanrosul.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.kontrakanprojects.al_kisah25nabidanrosul.R
import com.kontrakanprojects.al_kisah25nabidanrosul.databinding.FragmentDetailKisahNabiBinding
import com.kontrakanprojects.al_kisah25nabidanrosul.model.ResultsKisahNabi

class DetailKisahNabiFragment : Fragment() {

    private var _binding: FragmentDetailKisahNabiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailKisahNabiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = DetailKisahNabiFragmentArgs.fromBundle(arguments as Bundle).resultKisahNabi
        prepare(result)
        setToolbarTitle(result.name.toString())
    }

    private fun prepare(result: ResultsKisahNabi) {
        with(binding) {
            Glide.with(requireContext())
                .load(result.imageUrl)
                .placeholder(R.drawable.img_not_found)
                .error(R.drawable.img_not_found)
                .into(imgNabi)

            tvTitle.text = result.name
            tvUsia.text = result.usia
            tvDescription.text = result.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbarTitle(namaNabi: String) {
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        if ((activity as AppCompatActivity?)!!.supportActionBar != null) {
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Detail $namaNabi"
            (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}